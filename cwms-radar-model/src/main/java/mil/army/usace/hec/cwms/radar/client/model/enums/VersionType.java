package mil.army.usace.hec.cwms.radar.client.model.enums;

public enum VersionType {
    MAX_AGGREGATE,
    SINGLE_VERSION,
    UNVERSIONED;

    public static VersionType versionTypeFor(String versionType) {
        VersionType retval = null;

        if(versionType != null){
            retval = VersionType.valueOf(versionType.toUpperCase());
        }

        return retval;
    }
}

