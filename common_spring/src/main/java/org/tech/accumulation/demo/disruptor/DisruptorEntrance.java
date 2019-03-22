package org.tech.accumulation.demo.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <a href="https://blog.csdn.net/twypx/article/details/80398886"></a>
 * @author peiheng.jiang create on 2019/03/22
 */
public class DisruptorEntrance {

    private static final Logger logger = LoggerFactory.getLogger(DisruptorEntrance.class);

    private static final int DEF_RING_BUFFER_SIZE = 1024 * 1024;    // 环形队列长度，最好是2的N次方

    private static final int PARALLEL = 1;    // 并行计算
    private static final int SERIAL = 2;    // 串行计算
    private static final int DIAMOND = 3;    // 菱形计算
    private static final int CHAIN = 4;    // 链式并行计算
//    private static final int PARALLEL_WITH_POOL = 5;    // 并行池化计算
//    private static final int SERIAL_WITH_POOL = 6;    // 串行池化计算

    private static final ExecutorService EXEC = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) {
        DisruptorEntrance entrance = new DisruptorEntrance();
        entrance.common(PARALLEL, 10L);
    }

    private void common(int eventId, long arg) {
        EventFactory<LongEvent> factory = new LongEventFactory();
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, DEF_RING_BUFFER_SIZE, EXEC, ProducerType.SINGLE, new BlockingWaitStrategy());
        switch (eventId) {
            case PARALLEL : parallel(disruptor); break;
            case SERIAL : serial(disruptor); break;
            case DIAMOND : diamond(disruptor); break;
            case CHAIN : chain(disruptor); break;
            default : logger.error("Event id error, valid id range is 1~4, intpu event id:{}", eventId); return;
        }
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(new LongEventTranslator(), arg);
    }

    private void parallel(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler(), new C21EventHandler());
        disruptor.start();
    }

    private void serial(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler()).then(new C21EventHandler());
        disruptor.start();
    }

    private void diamond(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler(), new C12EventHandler()).then(new C21EventHandler());
        disruptor.start();
    }

    private void chain(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler());
        disruptor.handleEventsWith(new C21EventHandler()).then(new C22EventHandler());
        disruptor.start();
    }


}
