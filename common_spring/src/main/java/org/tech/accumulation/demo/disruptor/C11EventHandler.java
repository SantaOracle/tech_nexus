package org.tech.accumulation.demo.disruptor;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * long +10
 * @author peiheng.jiang create on 2019/03/22
 */
public class C11EventHandler implements EventHandler<LongEvent> {

    private static final Logger logger = LoggerFactory.getLogger(C11EventHandler.class);

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long num = event.getNumber();
        num += 10;
        logger.info("{} - C11 finish - {}", Thread.currentThread().getName(), num);
    }

}
