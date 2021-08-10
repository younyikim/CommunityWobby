package persistence.dao;

import java.util.List;
import service.dto.ScrapBoxDTO;

public interface ScrapBoxDAO {
   public List<ScrapBoxDTO> getScrapList(String userId);
   public int deleteScrap(String scarpId);
   public int createScrap(ScrapBoxDTO scrap);
   public int getPostIdbyScrap(int scrapNo);
   public boolean existingScrap(String userId, int postId);
	public int deleteScrapBypostId(int postId);
}