package org.tech.accumulation.demo.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author peiheng.jiang create on 2019/03/22
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
