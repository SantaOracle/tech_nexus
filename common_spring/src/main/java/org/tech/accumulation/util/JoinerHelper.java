package org.tech.accumulation.util;

import com.google.common.base.Splitter;
import org.tech.accumulation.constant.CharacterConsts;

/**
 * @author peiheng.jiang create on 2019/9/8
 */
public class JoinerHelper {

    public static final Splitter SUB_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_SUB).trimResults().omitEmptyStrings();
    public static final Splitter COLON_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_COLON).trimResults().omitEmptyStrings();
    public static final Splitter FORWARD_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_FORWARD).trimResults().omitEmptyStrings();
    public static final Splitter COMMA_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_COMMA).trimResults().omitEmptyStrings();
    public static final Splitter PIPE_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_PIPE).trimResults().omitEmptyStrings();
    public static final Splitter SEMICOLON_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_SEMICOLON).trimResults().omitEmptyStrings();
    public static final Splitter UNDERLINE_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_UNDERLINE).trimResults().omitEmptyStrings();
    public static final Splitter DOLLAR_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_DOLLAR).trimResults().omitEmptyStrings();
    public static final Splitter WAVE_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_WAVE).trimResults().omitEmptyStrings();
    public static final Splitter AMPERSAND_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_AMPERSAND).trimResults().omitEmptyStrings();
    public static final Splitter CARET_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_CARET).trimResults().omitEmptyStrings();
    public static final Splitter POINT_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_POINT).trimResults().omitEmptyStrings();
    public static final Splitter AT_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_AT).trimResults().omitEmptyStrings();
    public static final Splitter CHARACTER_WELL_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_WELL).trimResults().omitEmptyStrings();



    public static final Splitter COMMA_WHITH_EMPTY_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_COMMA).trimResults();
    public static final Splitter PIPE_WITH_EMPTY_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_PIPE).trimResults();
    public static final Splitter SUB_WITH_EMPTY_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_SUB).trimResults();
    public static final Splitter COLON_WITH_EMPTY_SPLITTER = Splitter.on(CharacterConsts.CHARACTER_COLON).trimResults();
}
