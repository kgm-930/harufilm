package com.ssafy.harufilm.service.article;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.dto.hash.HashRequestDto;
import com.ssafy.harufilm.entity.Article;
import com.ssafy.harufilm.entity.Hash;
import com.ssafy.harufilm.entity.Hashtag;
import com.ssafy.harufilm.repository.article.ArticleRepository;
import com.ssafy.harufilm.repository.hash.HashRepository;
import com.ssafy.harufilm.repository.hashtag.HashtagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    HashRepository hashRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Override
    public Article articleSave(ArticleRequestDto articleRequestDto) throws IllegalStateException, IOException {


        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date time = new Date();

        String now = format.format(time);


        // userpid폴더가 있는지 검사한다.
        String path = "/var/opt/upload/article/" + articleRequestDto.getUserpid();
        File folder = new File(path);

        if(!folder.exists()){
            try{
                folder.mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

         //해당userpid폴더에 오늘날짜가 있는지 검사한다.
        path = "/var/opt/upload/article/" + articleRequestDto.getUserpid() +"/" + now;
        folder = new File(path);

        if(!folder.exists()){
            try{
                folder.mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }

        //해당 날짜에 파일을 저장한다.
        // System.out.println(originfile.getOriginalFilename());

        // 이미지 저장
        try {
            List<MultipartFile> imgfile = articleRequestDto.getImgdata();
            for(int i=0;i<imgfile.size();++i){

                MultipartFile originfile = imgfile.get(i);
                String filename = originfile.getOriginalFilename();
                int idx = -1;
                idx = filename.lastIndexOf(".");
              
                String ChangeFilename =    Integer.toString(i+1)+filename.substring(idx, filename.length());
                File UpdateFile = new File(path+"/"+ChangeFilename);
        
                originfile.transferTo(UpdateFile);
                }
        } catch (Exception e) {
            System.out.println("no Data");
            //TODO: handle exception
        }


           //비디오 저장
        try {
        List<MultipartFile> videofile = articleRequestDto.getVideodata();

        for(int i=0;i<videofile.size();++i){

            MultipartFile originfile = videofile.get(i);
    
            String filename = originfile.getOriginalFilename();
            int idx = -1;
            idx = filename.lastIndexOf(".");
          
            String ChangeFilename =    Integer.toString(i+1)+filename.substring(idx, filename.length());
            File UpdateFile = new File(path+"/"+ChangeFilename);
    
            originfile.transferTo(UpdateFile);
         }
            
        } catch (Exception e) {
            System.out.println("no Data");
            //TODO: handle exception
        }

        Article article = Article.builder()
                .articlethumbnail(articleRequestDto.getArticlethumbnail())
                .articleshare(0)
                .userpid(articleRequestDto.getUserpid())
                .articlecreatedate(now)
                .build();

        Article savedArticle = articleRepository.save(article);

        // 아티클 저장

        // List<HashRequestDto> hashlist = articleRequestDto.getHashlist();

        // for (int i = 0; i < hashlist.size(); i++) {
        //     String hashname = hashlist.get(i).getHashname();

        //     Hash h;
        //     h = hashRepository.findByHashname(hashname).orElse(null);

        //     if (h == null) {
        //         h = Hash.builder()
        //                 .hashname(hashname)
        //                 .build();

        //         hashRepository.save(h);
        //     }
        //     Hashtag ht;
        //     ht = Hashtag.builder()
        //             .hashidx(h.getHashidx())
        //             .articleidx(savedArticle.getArticleidx())
        //             .build();
        //     hashtagRepository.save(ht);
        // }

        return savedArticle;
    }

    @Override
    public void articleDelete(int articleidx) {
        Article article = articleRepository.findByArticleidx(articleidx).orElse(null);

        if (article != null) {
            
            String path = "/var/opt/upload/article/" + article.getUserpid() +"/" +article.getArticlecreatedate();
            File folder = new File(path);
    
            if(folder.exists()){
                try{
                    folder.delete();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            //해당 날짜 폴더 삭제

            hashtagRepository.deleteAllByArticleidx(article.getArticleidx()); // 해시태그리스트에서 삭제
          
            articleRepository.deleteById(articleidx);
            //테이블 삭제
        }

    }

    @Override
    public List<Article> getmyArticle(int userpid) {
        List<Article> list = 
        articleRepository.findAllByUserpid(userpid);
        
        return list;
    }

    @Override
    public List<Article> getFollowedArticleList(int userpid) {
        List<Article> list = 
        articleRepository.articleList(userpid);
        
        return list;
    }

    // @Override
    // public Article findByArticleidx(int articleidx) {
    //     Article article = articleRepository.findByArticleidx(articleidx).orElse(null);
    //     return article;
    // }

    @Override
    public List<Article> getarticlelistbykeyword(String keyword) {
        List<Article> articlelist = articleRepository.findByHashnameItContainsKeyword(keyword);
        return articlelist;
    }
}
