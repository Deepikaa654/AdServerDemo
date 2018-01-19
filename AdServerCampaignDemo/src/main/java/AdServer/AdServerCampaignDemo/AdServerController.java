package AdServer.AdServerCampaignDemo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class AdServerController {
	
		@RequestMapping(value = "/ad", method = { RequestMethod.POST })
		@ResponseBody
		public ResponseEntity<?> addCampaign(WebRequest request) {
			Long partnerId = new Long(request.getParameter("partner_id"));
			Long duration = new Long(request.getParameter("duration"));
			String adContent = request.getParameter("ad_content");
			Campaign campaign = new Campaign(partnerId, duration, adContent);
 
			List<Campaign> campaignList = CampaignRepository.findByPartnerId(campaign.getPartnerID());

			if (campaignList.isEmpty()) {

				CampaignRepository.save(campaign);

				return new ResponseEntity(new CustomErrorMessage(
						"New Partner Campaign created for Partner ID : " + campaign.getPartnerID() + " Sucessfully"),
						HttpStatus.CREATED);

			} else {
				// create a comparator object using a Lambda expression
				Comparator<Campaign> compareCampaign = (d1, d2) -> d1.compareTo(d2);

				// Sort the Collection in this case 'campaignList' in reverse order
				Collections.sort(campaignList, Collections.reverseOrder(compareCampaign));

				for (Campaign temp : campaignList) {
					if (DateTime.campaingDuration(temp.getCreationDTime(), temp.getDuration())) {
						campaignRepository.save(campaign);
						return new ResponseEntity(
								new CustomErrorMessage("Campaign created for Partner ID : " + campaign.getPartnerID() + " Sucessfully"),HttpStatus.CREATED);
					}
				}
			}
			return new ResponseEntity(new CustomErrorMessage("Unable to create Campaign. A Partner ID : " + campaign.getPartnerID() + " Campaign already exsit"),HttpStatus.CONFLICT);
		}

		/**
		 * Get Request to return All Active Campaigns related to the Partner
		 * 
		 * @param partnerId
		 * @return List of Active Campgains
		 */
		@RequestMapping(value = "/ad/{partner_id}")
		@ResponseBody
		public ResponseEntity<?> getCampaign(@PathVariable("partner_id") Long partnerID) {
			List<Campaign> campaignList = CampaignRepository.findByPartnerId(partnerID);
			if (campaignList.isEmpty()) {
				return new ResponseEntity(new CustomErrorMessage("No Campaign Found for Partner ID : " + partnerID),
						HttpStatus.NOT_FOUND);
			} else {
				for (Campaign temp : campaignList) {
					if (DateTime.campaingDuration(temp.getCreationDTime(), temp.getDuration())) {
						return new ResponseEntity(
								new CustomErrorMessage("No Active Campaign Found for Partner id : " + temp.getPartnerID()),
								HttpStatus.NOT_FOUND);
					}
				}
			}
			return new ResponseEntity<>(campaignList, HttpStatus.OK);
		}

		private final PartnerRepository partnerRepository;
		private final CampaignRepository campaignRepository;

		@Autowired
		public AdServerController(PartnerRepository partnerRepository, CampaignRepository campaignRepository)
		{
			this.partnerRepository = partnerRepository;
			this.campaignRepository = campaignRepository;
		}

		
	

}
