package com.xiaomi.merchant.infastructure;

import com.xiaomi.merchant.infastructure.dao.SeqDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.stereotype.Component;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class SerialNumGenerator{

    private final static Map<String, Pair<AtomicLong,Long>> SERIAL_IDX_MAP = new ConcurrentHashMap();

    private static SeqDao seqDao;

    private static volatile boolean ready = false;

    private final static int SERIAL_NUM_STR_SIZE = 19;

    private final static int SERIAL_GET_AND_INCREMENT_STEP = 1000;

    public static String getNextSerialNum(String serialName){
        if(!ready){
            throw new BeanCurrentlyInCreationException("SerialNumGenerator not ready");
        }
        while(true) {
            Pair<AtomicLong, Long> atomicLongLongPair = SERIAL_IDX_MAP.get(serialName);
            if (atomicLongLongPair != null) {
                Long nextSerial = atomicLongLongPair.getLeft().getAndIncrement();
                if (nextSerial < atomicLongLongPair.getRight()) {
                    return StringUtils.leftPad(nextSerial.toString(), SERIAL_NUM_STR_SIZE, '0');
                }
            }
            tryUpdateSerial(serialName,atomicLongLongPair);
        }

    }

    private static ConcurrentHashMap<String, Reference<Object>> Lock_Map = new ConcurrentHashMap();

    private static void tryUpdateSerial(String serialName,Pair expectSerialPair) {
        if(Lock_Map.putIfAbsent(serialName,new SoftReference<>(null)) != null){
            return;
        }
        try{
            if(SERIAL_IDX_MAP.get(serialName) != expectSerialPair){
                //double check fail;
                return;
            }
            while (true) {
                Long seqLastIdx = seqDao.getSeqLastIdx(serialName);
                if(seqLastIdx == null){
                    try {
                        seqDao.insertSeq(serialName);
                    }catch (Exception e){
                        //ignore dumplicate key or other error
                    }
                    continue;
                }
                long nextSeqIdx = seqLastIdx + SERIAL_GET_AND_INCREMENT_STEP;
                int i = seqDao.updateLastIdxWithCasMode(serialName, nextSeqIdx, seqLastIdx);
                if (i == 1) {
                    SERIAL_IDX_MAP.put(serialName, new ImmutablePair<>(new AtomicLong(seqLastIdx), nextSeqIdx));
                    break;
                }
            }
        }finally {
            Lock_Map.remove(serialName);
        }


    }

    public SerialNumGenerator(SeqDao seqDao){
        SerialNumGenerator.seqDao = seqDao;
        SerialNumGenerator.ready = true;
    }







}
