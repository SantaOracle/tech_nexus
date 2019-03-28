package org.tech.accumulation.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 注意，在使用本类时，遇到多层嵌套类型，需要进行手工定制其反序列化，具体详见：
 * <a href="https://www.baeldung.com/jackson-nested-values"></a>
 *
 * @author yuanwei on 2018/12/28
 **/
@Slf4j
public final class JsonUtil {

    public static final String NULL_VALUE = "null";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String DEF_DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    static {
        // 保证对象版本兼容性，忽略未知属性
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        // 序列化时，遇到Empty属性的，不进行序列化
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 序列化DATE的时候，指定默认序列化格式
        MAPPER.setDateFormat(new SimpleDateFormat(DEF_DATETIME_FORMAT_PATTERN));
    }

    private JsonUtil() {
    }

    /**
     * 根据 json串 和 path 获取指定的Node节点
     * 1. 解析Json失败，返回null
     * 2. 找不到节点，返回 {@link com.fasterxml.jackson.databind.node.MissingNode}
     * 3. 找到节点的值为null，返回 {@link com.fasterxml.jackson.databind.node.NullNode}
     * 4. 找到节点，返回 {@link JsonNode}
     *
     * @param json Json字符串
     * @param path {@link com.fasterxml.jackson.core.JsonPointer}
     */
    @Nullable
    public static JsonNode extractJsonNode(@NonNull String json, @NonNull String path) {
        try {
            JsonNode node = MAPPER.readTree(json);
            return node.at(path);
        } catch (IOException e) {
            log.error("Read as json node failed, json={}, path={}", json, path, e);
            return null;
        }
    }

    /**
     * 提取指定json的指定路径的字符串，若指定路径是一个值节点，那么就返回其值，否则返回该节点的toString
     */
    @Nullable
    public static String extractString(@NonNull String json, @NonNull String path) {
        JsonNode node = extractJsonNode(json, path);
        if (node == null || node.getClass() == MissingNode.class) {
            return null;
        }
        if (node.getClass() == NullNode.class) {
            return NULL_VALUE;
        }
        if (node.isValueNode()) {
            return node.textValue();
        }
        return node.toString();
    }

    @NonNull
    public static String toString(@NonNull Object t) {
        try {
            return MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.error("Convert to json failed, obj={}", t, e);
            return StringUtils.EMPTY;
        }
    }

    @Nullable
    public static <T> T toObject(@NonNull String s, @NonNull Class<T> klass) {
        try {
            return MAPPER.readValue(s, klass);
        } catch (IOException e) {
            log.error("Convert to {} failed, string={}, ", klass.getSimpleName(), s, e);
            return null;
        }
    }

    /**
     * 根据 JSON 和 List 的元素类型 componentClass 进行反序列化
     */
    @NonNull
    public static <T> List<T> toList(@NonNull String s, @NonNull Class<T> componentClass) {
        try {
            CollectionType type = MAPPER.getTypeFactory().constructCollectionType(List.class, componentClass);
            return MAPPER.readValue(s, type);
        } catch (IOException e) {
            log.error("Convert to List<{}> failed, string={}, ", componentClass.getSimpleName(), s, e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据 JSON 和 Set 的元素类型 componentClass 进行反序列化
     */
    @NonNull
    public static <T> Set<T> toSet(@NonNull String s, @NonNull Class<T> componentClass) {
        try {
            CollectionType type = MAPPER.getTypeFactory().constructCollectionType(Set.class, componentClass);
            return MAPPER.readValue(s, type);
        } catch (IOException e) {
            log.error("Convert to Set<{}> failed, string={}, ", componentClass.getSimpleName(), s, e);
            return Collections.emptySet();
        }
    }

    /**
     * 根据 JSON 和 Map 的元素类型 keyClass,valueClass 进行反序列化
     */
    @NonNull
    public static <K, V> Map<K, V> toMap(@NonNull String s, @NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(Map.class, keyClass, valueClass);
            return MAPPER.readValue(s, javaType);
        } catch (IOException e) {
            log.error("Convert to Map<{}, {}> failed, string={}, ",
                    keyClass.getSimpleName(), valueClass.getSimpleName(), s, e);
            return Collections.emptyMap();
        }
    }

    /**
     * 根据 JSON 和 Array 的类型 arrayClass 进行反序列化
     */
    @NonNull
    public static <T> T[] toArray(@NonNull String s, @NonNull Class<T[]> arrayClass) {
        try {
            return MAPPER.readValue(s, arrayClass);
        } catch (IOException e) {
            log.error("Convert to {} failed, string={}, ", arrayClass.getSimpleName(), s, e);
            try {
                return arrayClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e1) {
                throw new IllegalArgumentException("Can not initialize an array of " + arrayClass.getSimpleName(), e1);
            }
        }
    }
}
