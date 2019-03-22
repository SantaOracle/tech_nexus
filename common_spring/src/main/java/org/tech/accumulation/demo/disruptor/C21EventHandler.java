package org.tech.accumulation.demo.disruptor;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * num += 20
 * @author peiheng.jiang create on 2019/03/22
 */
public class C21EventHandler implements EventHandler<LongEvent> {

    private static final Logger logger = LoggerFactory.getLogger(C21EventHandler.class);

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long num = event.getNumber();
        num += 20;
        logger.info("{} - C21 finish - {}", Thread.currentThread().getName(), num);
    }
}
