package encrypt.DataSource;

/*
 * Author:fcy
 * Date:2020/3/28 3:28
 */
public interface encrypt {
    public String encryptPassword(String str) throws Exception;
    public String decryptPassword(String str) throws Exception;
}
