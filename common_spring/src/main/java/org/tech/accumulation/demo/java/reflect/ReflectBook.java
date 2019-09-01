package org.tech.accumulation.demo.java.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author peiheng.jiang create on 2019/8/31
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReflectBook {

    private static final String TAG = "BookTag";

    private String name;
    private String author;

    private String declaredMethod(int index) {
        String string = null;
        switch (index) {
            case 0:
                string = "I am declaredMethod 1 !";
                break;
            case 1:
                string = "I am declaredMethod 2 !";
                break;
            default:
                string = "I am declaredMethod 1 !";
        }

        return string;
    }
}
