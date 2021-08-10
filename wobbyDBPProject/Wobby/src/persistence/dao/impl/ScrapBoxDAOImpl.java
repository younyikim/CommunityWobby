package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.ScrapBoxDAO;
import service.dto.ScrapBoxDTO;

// �쐻 猷� �굲 �쐻 猷� �굲 �쐻 猷� �굲  �쐻 �솯�ⓦ끉�굲
public class ScrapBoxDAOImpl implements ScrapBoxDAO {

    private JDBCUtil jdbcUtil = null;

    public ScrapBoxDAOImpl() {
        jdbcUtil = new JDBCUtil();
    }

    /**
     *  �쐻 猷� �굲力�  Scrap  �쐻 猷� �굲 �쐻 猷� �굲 �뱜  �쐻 猷� �굲 �넎
     */
    public List<ScrapBoxDTO> getScrapList(String userId) {
        String query = "SELECT SCRAP_NO, POST_ID, SCRAPDATE "
                + "FROM SCRAP "
                + "WHERE USER_ID = ? "
                + "ORDER BY SCRAPDATE DESC";
        jdbcUtil.setSqlAndParameters(query, new Object[] { userId });
        
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            List<ScrapBoxDTO> list = new ArrayList<ScrapBoxDTO>(); 
            while (rs.next()) {
                ScrapBoxDTO dto = new ScrapBoxDTO(); 
                dto.setScrapNo(rs.getInt("SCRAP_NO"));
                dto.setPostId(rs.getInt("POST_ID"));
                dto.setScrapDate(rs.getDate("SCRAPDATE"));
                System.out.println(rs.getInt("SCRAP_NO") + "/" + rs.getInt("POST_ID") + "/" + rs.getDate("SCRAPDATE"));
                list.add(dto);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // ResultSet, PreparedStatement, Connection  �쐻 猷� �굲 �넎
        }
        return null;
    }

    /**
     * Post �쐻 猷� �굲 ScrapBox �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲
     */
    public int createScrap(ScrapBoxDTO scrap) {
        String sql = "INSERT INTO SCRAP VALUES (?, ?, SCRAP_SEQ.NEXTVAL, SYSDATE)";

        Object[] param = new Object[] { scrap.getPostId(), scrap.getUserId() };
        jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil  �쐻 猷� �굲 insert �쐻 猷� �굲 �쐻 猷� �굲  �쐻 �뻿�ⓦ끉�굲  �쐻 猷� �굲 �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲

        try {
            int result = jdbcUtil.executeUpdate(); 
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource  �쐻 猷� �굲 �넎
        }
        return 0;
    }

    /**
     * Scrap �쐻 猷� �굲 Post �쐻 猷� �굲 �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲
     */
    public int deleteScrap(String ScrapNo) {
        String sql = "DELETE FROM SCRAP WHERE SCRAP_NO = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { ScrapNo }); // JDBCUtil �쐻 猷� �굲 delete �쐻 猷� �굲 �쐻 猷� �굲  �쐻 �뻿�ⓦ끉�굲  �쐻 猷� �굲 �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲

        try {
            int result = jdbcUtil.executeUpdate(); // delete  �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource  �쐻 猷� �굲 �넎
        }
        return 0;
    }
    
    public int increaseNumOfScraps(int postId) {
        String sql = "UPDATE POST SET NUMOFSCRAPS = (SELECT COUNT(SCRAP_NO) FROM SCRAP" + 
    " WHERE POST_ID= ? ) + 1 WHERE POST_ID = ? ";   
        jdbcUtil.setSqlAndParameters(sql,  new Object[] {postId, postId});   // JDBCUtil �쐻 �윥 諭� update  �떛怜얇꺂梨뜻쾮  蒻븍슢�뵞 嫄�     �녃域밟뫁�굲 �쐻 �윥 鍮�  �쐻 �윞 留� �쐻 �윪 �젳
           
        try {            
           int result = jdbcUtil.executeUpdate();   // update   �떛 �쐻   �쐻 �윥�젆袁��쐻 �윥�뀷 
           return result;
        } catch (Exception ex) {
           jdbcUtil.rollback();
           ex.printStackTrace();
        }
        finally {
           jdbcUtil.commit();
           jdbcUtil.close();   // resource  �쎗 猷뉒몭   瑗�
        }      
        return 0;
    }
    

    public int deleteScrap(int scrapNo) {
        String sql = "DELETE FROM SCRAP WHERE SCRAP_NO = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { scrapNo }); // JDBCUtil �쐻 猷� �굲 delete �쐻 猷� �굲 �쐻 猷� �굲  �쐻 �뻿�ⓦ끉�굲  �쐻 猷� �굲 �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲

        try {
            int result = jdbcUtil.executeUpdate(); // delete  �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource  �쐻 猷� �굲 �넎
        }
        return 0;
    }

    public int decreaseNumOfScraps(int postId) {
        String sql = "UPDATE POST SET NUMOFSCRAPS = (SELECT COUNT(SCRAP_NO) FROM SCRAP" + 
    " WHERE POST_ID= ? ) - 1 WHERE POST_ID = ?";   
        jdbcUtil.setSqlAndParameters(sql,  new Object[] {postId, postId});   // JDBCUtil �쐻 �윥 諭� update  �떛怜얇꺂梨뜻쾮  蒻븍슢�뵞 嫄�     �녃域밟뫁�굲 �쐻 �윥 鍮�  �쐻 �윞 留� �쐻 �윪 �젳
           
        try {            
           int result = jdbcUtil.executeUpdate();   // update   �떛 �쐻   �쐻 �윥�젆袁��쐻 �윥�뀷 
           return result;
        } catch (Exception ex) {
           jdbcUtil.rollback();
           ex.printStackTrace();
        }
        finally {
           jdbcUtil.commit();
           jdbcUtil.close();   // resource  �쎗 猷뉒몭   瑗�
        }      
        return 0;
    }

    public int getPostIdbyScrap(int scrapNo) {
        String query = "SELECT POST_ID "
                + "FROM SCRAP "
                + "WHERE SCRAP_NO = ? ";
        jdbcUtil.setSqlAndParameters(query, new Object[] { scrapNo });
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString("POST_ID"));
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // ResultSet, PreparedStatement, Connection  �쐻 猷� �굲 �넎
        }
        return 0;
    }
    

    public boolean existingScrap(String userId, int postId) {
       String sql = "SELECT count(*) FROM SCRAP WHERE USER_ID= ? AND POST_ID = ? ";
       jdbcUtil.setSqlAndParameters(sql, new Object[] { userId, postId });

       try {
          ResultSet rs = jdbcUtil.executeQuery();
          if (rs.next()) {
             int count = rs.getInt(1);
             return (count == 1 ? true : false);
          }
       } catch (Exception ex) {
          ex.printStackTrace();
       } finally {
          jdbcUtil.close();
       }
       return false;
    }

    public int deleteScrapBypostId(int postId) {
        String sql = "DELETE FROM SCRAP WHERE POST_ID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { postId }); // JDBCUtil �쐻 猷� �굲 delete �쐻 猷� �굲 �쐻 猷� �굲  �쐻 �뻿�ⓦ끉�굲  �쐻 猷� �굲 �쐻 猷� �굲  �쐻 猷� �굲 �쐻 猷� �굲
        
        System.out.print(postId+"가 삭제");
        try {
            int result = jdbcUtil.executeUpdate(); 
            System.out.print(result+"가 결과!");
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close(); // resource  �쐻 猷� �굲 �넎
        }
        return 0;
    }

}