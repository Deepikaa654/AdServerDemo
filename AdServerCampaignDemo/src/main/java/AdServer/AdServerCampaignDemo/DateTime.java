package AdServer.AdServerCampaignDemo;

import java.time.*;

public class DateTime {

	public static boolean campaingDuration(LocalDateTime creationDateTime, Long campaignDuration) {
		LocalDateTime time1 = creationDateTime;
		Duration durationSeconds = Duration.ofSeconds(campaignDuration);
		time1 = time1.plus(durationSeconds);
		LocalDateTime time2 = LocalDateTime.now();
		System.out.println("creationTime: " + time1 + " Now: " + time2);
		Duration duration = Duration.between(time1, time2);
		System.out.println("Duration: " + duration.getSeconds());
		return (duration.getSeconds() > 0) ? true : false;
	}

}
