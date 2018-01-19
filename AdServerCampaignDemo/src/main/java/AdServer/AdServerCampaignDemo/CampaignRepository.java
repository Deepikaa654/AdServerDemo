package AdServer.AdServerCampaignDemo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="campaign", path="campaign")
public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Long>  {
	
	 static List<Campaign> findByPartnerId(@Param("partnerID") Long partnerID) {
		// TODO Auto-generated method stub
		return null;
	}
}
