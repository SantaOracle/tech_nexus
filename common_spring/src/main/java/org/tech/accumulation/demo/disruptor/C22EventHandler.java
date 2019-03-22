package org.tech.accumulation.demo.disruptor;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peiheng.jiang create on 2019/03/22
 */
public class C22EventHandler implements EventHandler<LongEvent> {

    private static final Logger logger = LoggerFactory.getLogger(C22EventHandler.class);

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long num = event.getNumber();
        num *= 20;
        logger.info("{} - C22 finish - {}", Thread.currentThread().getName(), num);
    }

}
