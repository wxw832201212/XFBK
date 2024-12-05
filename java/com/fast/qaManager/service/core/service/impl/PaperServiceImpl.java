package com.fast.qaManager.service.core.service.impl;

import com.fast.qaManager.service.core.buffer.LocalBuffer;
import com.fast.qaManager.service.core.dao.PaperDao;
import com.fast.qaManager.service.core.entity.Paper;
import com.fast.qaManager.service.core.entity.User;
import com.fast.qaManager.service.core.entity.vo.Response;
import com.fast.qaManager.service.core.enums.ErrorType;
import com.fast.qaManager.service.core.exceptions.AssertException;
import com.fast.qaManager.service.core.service.PaperService;
import com.fast.qaManager.service.core.util.Assert;
import com.fast.qaManager.service.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperDao paperDao;

    @Override
    public Response list(String token) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            List<Paper> papers = paperDao.findAll();

            return ResponseUtil.success("papers",papers);
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }

    @Override
    public Response listByParams(String token, Map<String, Object> map) {
        try{
            User loginUser = LocalBuffer.loginUserBuffer.get(token);
            Assert.asserts(null != loginUser, ErrorType.USER_SESSION_IS_TIME_OUT);

            Specification<Paper> spec = new Specification<Paper>() {
                @Override
                public Predicate toPredicate(Root<Paper> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    //1.获取比较的属性
                    Path<Object> title = root.get("title");
                    Path<Object> subject = root.get("subject");
                    Path<Object> releaseYear = root.get("releaseYear");
                    Predicate predicate = null;
                    Predicate p1 = null;
                    Predicate p2 = null;
                    Predicate p3 = null;
                    List<Predicate> predicateList = new ArrayList<Predicate>();

                    if(!"^all".equals(map.get("title").toString())){
                        p1 = criteriaBuilder.equal(title, map.get("title").toString());
                        predicateList.add(p1);
                    }
                    if(!"所有科目".equals(map.get("subject").toString())){
                        p2 = criteriaBuilder.equal(subject, map.get("subject").toString());
                        predicateList.add(p2);
                    }
                    if(!"所有年份".equals(map.get("releaseYear").toString())){
                       p3 = criteriaBuilder.equal(releaseYear, map.get("releaseYear").toString());
                        predicateList.add(p3);
                    }

                    predicate = criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));

                    return predicate;
                }
            };

            List<Paper> papers = paperDao.findAll(spec);
            return ResponseUtil.success("papers",papers);
        }catch (AssertException e){
            return ResponseUtil.assertError(e);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.systemError();
        }
    }

    @Override
    public void download(HttpServletResponse response, String token, Integer paperId) {
        try{
            Paper paper = paperDao.findPaperById(paperId);
            File file = new File(paper.getPath());
            String pdfPath = paper.getPath();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("content-disposition", "attachment; filename=" + file.getName() + ".pdf");
            response.setHeader("Access-Control-Expose-Headers","jid");
            response.setHeader("jid",file.getName());
            writeBytes(pdfPath, response.getOutputStream());
            File pdfFile = new File(pdfPath);
            if (pdfFile.exists()) {
                DataOutputStream temps = new DataOutputStream(response.getOutputStream());
                DataInputStream in = new DataInputStream(new FileInputStream(pdfPath));
                byte[] b = new byte[2048];
                while ((in.read(b)) != -1) {
                    temps.write(b);
                    temps.flush();
                }
                in.close();
                temps.close();
            } else {
                System.out.println("文件不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }
}
