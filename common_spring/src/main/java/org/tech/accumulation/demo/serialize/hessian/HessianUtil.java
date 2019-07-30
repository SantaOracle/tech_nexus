package org.tech.accumulation.demo.serialize.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import lombok.extern.slf4j.Slf4j;
import org.tech.accumulation.bean.User;

import java.io.*;

/**
 * create by peiheng.jiang on 2019/7/30
 */
@Slf4j
public class HessianUtil {

    public static <T extends Serializable> byte[] serialize(T obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(bos);

        try {
            ho.writeObject(obj);
            ho.flushBuffer();
        } catch (IOException e) {
            log.error("Hessian serialize failed, e:", e);
        }

        return bos.toByteArray();
    }

    public static <T extends Serializable> T desrialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Hessian2Input hi = new Hessian2Input(bis);
        Object obj = null;

        try {
            obj = hi.readObject();
        } catch (IOException e) {
            log.error("Hessian deserialize failed, e:", e);
        }

        return (T) obj;
    }

    public static void main(String[] args) {
        User user = new User().setUsername("Santa").setPassword("123").setGender(1).setPhone("1897710000");

        byte[] bytes = serialize(user);
        User user2 = desrialize(bytes);
        log.info("User:{}", user2);
    }
}
