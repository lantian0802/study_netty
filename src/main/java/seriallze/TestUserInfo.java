package seriallze;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by jianying.wcj on 2015/2/1 0001.
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is :" + b.length);
        bos.close();
        System.out.println("---------------------------------------------");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("The byte array serializable length is:"+ info.codeC(buffer).length);
    }
}
