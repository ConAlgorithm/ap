package util;

import org.apache.commons.codec.binary.Base64;
 
import java.util.UUID;
 
public final class UuidUtil 
{
	private UuidUtil()
	{
		
	}
	
	public static String uuid() 
	{
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
 
    /**
     * @return a random short uuid derived from UUID.
     */
    public static String compressedUuid() 
    {
        UUID uuid = UUID.randomUUID();
        return compressedUUID(uuid);
    }
 
    protected static String compressedUUID(UUID uuid) 
    {
        byte[] byUuid = new byte[8 + 8];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);
//        String compressUUID = Base64.encodeBase64URLSafeString(byUuid);
        String compressUUID = Base64.encodeBase64String(byUuid).replaceAll("\\=|\n|\r", "");
        compressUUID = compressUUID.replace("+", "-");
        compressUUID = compressUUID.replace("/", "(");
        return compressUUID;
    }
 
    protected static void long2bytes(long value, byte[] bytes, int offset) 
    {
        for (int i = 7; i > -1; i--) 
        {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }
 
    /**
     * @param uuidString a UUID string to be compressed.
     * @return compressed short uuid derived from uuidString.
     */
    public static String compress(String uuidString) 
    {
        UUID uuid = UUID.fromString(uuidString);
        return compressedUUID(uuid);
    }
 
    /**
     * @param compressedUuid a compressed uuid.
     * @return the UUID that generates compressedUuid.
     */
    public static String uncompress(String compressedUuid) 
    {
        if (compressedUuid.length() != 22) 
        {
            throw new IllegalArgumentException("Invalid uuid!");
        }
        compressedUuid = compressedUuid.replace("(", "/");
        byte[] byUuid = Base64.decodeBase64(compressedUuid + "==");
        long most = bytes2long(byUuid, 0);
        long least = bytes2long(byUuid, 8);
        UUID uuid = new UUID(most, least);
        return uuid.toString();
    }
 
    protected static long bytes2long(byte[] bytes, int offset) 
    {
        long value = 0;
        for (int i = 7; i > -1; i--) 
        {
            value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
        }
        return value;
    }
    
    public static void main(String[] args)
    {
    	System.out.println(UuidUtil.compress("6F5D7328-ED4F-E511-98E3-AC853DA49BEC"));
    	System.out.println(UuidUtil.compress("6CF93FBD-A582-E511-ACD8-AC853D9F5508".toLowerCase()));
//    	System.out.println(UuidUtil.uncompress("bPk/vaWC5RGs2KyFPZ9VCA").toUpperCase());
    	System.out.println(UuidUtil.uncompress("bPk(vaWC5RGs2KyFPZ9VCA").toUpperCase());
    	System.out.println(UuidUtil.compress("43D22CFA-109B-458F-8695-A286AF66F756"));
    	System.out.println(UuidUtil.uncompress("Q9Is-hCbRY-GlaKGr2b3Vg").toUpperCase());
    }
}