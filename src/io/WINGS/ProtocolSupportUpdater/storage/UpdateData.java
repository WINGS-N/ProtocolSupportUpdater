package io.WINGS.ProtocolSupportUpdater.storage;

public interface UpdateData {

	//GetJAR
		String JenkinsURL = "https://build.true-games.org/job/ProtocolSupport/lastSuccessfulBuild/artifact/target/";
		String ext = ".jar";
		
	//GetJAR backup server
		String BackupURL = "https://ci.velocitypowered.com/job/ProtocolSupport/lastSuccessfulBuild/artifact/target/";
}
