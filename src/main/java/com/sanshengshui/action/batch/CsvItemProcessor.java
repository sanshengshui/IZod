package com.sanshengshui.action.batch;

import com.sanshengshui.action.entity.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * @author 穆书伟
 * @description 数据处理及效验
 * @date 2018年3月8日 下午16:25
 */
public class CsvItemProcessor extends ValidatingItemProcessor<Person>{
    @Override
    public Person process(Person item) throws ValidationException {
        /**
         * 需执行super.process(item)才会调用自定义效验器。
         */
         super.process(item);

        /**
         * 对数据做简单的处理，若民族为汉族，
         * 则数据转换成01,其余转换成02
         */
        if (item.getNation().equals("汉族")){
            item.setNation("01");
        }else {
            item.setNation("02");
        }

        return item;
    }
}
