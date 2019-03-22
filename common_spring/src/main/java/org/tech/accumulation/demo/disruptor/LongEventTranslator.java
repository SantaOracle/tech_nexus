package org.tech.accumulation.demo.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * @author peiheng.jiang create on 2019/03/22
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {

    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        event.setNumber(arg0);
    }
}
