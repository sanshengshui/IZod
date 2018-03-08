package com.sanshengshui.action.batch;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;
import java.util.Set;

/**
 * @author 穆书伟
 * @description 效验
 * @date 2018年3月8日 下午16:38
 */
public class CsvBeanValidator<T> implements Validator<T>,InitializingBean {


    private javax.validation.Validator validator;

    /**
     * 使用JSR-303的Validator来效验我们的数据，在此处进行JSR-303的
     * Validator的初始化。
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory =
                Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
   public void validate(T value) throws ValidationException {
        /**
         * 使用Validator的validate方法效验数据
         */
        Set<ConstraintViolation<T>> constraintViolations =
                validator.validate(value);
        if (constraintViolations.size()>0){
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations){
                message.append(constraintViolation.getMessage()+ "\n");
            }
            throw new ValidationException(message.toString());
        }
   }
}
