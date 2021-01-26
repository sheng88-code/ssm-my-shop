package com.funtl.my.shop.commons.validator;


import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;

/**
 * jsr303
 */
public class BeanValidator {


    private static Validator validator;

    public static void setValidator(Validator validator){
        BeanValidator.validator=validator;
    }

    /**
     * 调用JSR303的validate的方法，验证失败时抛出 ConstraintViolationException
     * @param validator
     * @param object
     * @param groups
     * @throws ConstraintViolationException
     */
    public static void validateWithException(Validator validator,Object object,Class<?>...groups) throws ConstraintViolationException{
        Set constraintViolations=validator.validate(object,groups);
        if(!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    /**
     * 辅助方法，转换Set<constraintViolation>为lisT<message>
     */
    private static List<String> extractMessage(ConstraintViolationException e){

        return extractMessage(e.getConstraintViolations());
    }

    /**
     * 辅助方法，转换Set<constraintViolation>为lisT<message>
     */
    private static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations){
        List<String> erroeMessages=new ArrayList<>();
        for (ConstraintViolation violation:constraintViolations){
            erroeMessages.add(violation.getMessage());
        }
        return erroeMessages;
    }

    /**
     * 辅助方法，转化ConstraintViolationException中的Set<ConstraintViolation>为Map<property,message>
     */
    private static Map<String,String> extractPropertyAndMessage(ConstraintViolationException e){
        return extractPropertyAndMessage((ConstraintViolationException) e.getConstraintViolations());
    }

    /**
     * 辅助方法，转换Set<ConstraintViolation>为Map<property,message>
     */
    private static Map<String,String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations){
        Map<String,String> errorMessages=new HashMap<>();
        for (ConstraintViolation violation:constraintViolations){
            errorMessages.put(violation.getPropertyPath().toString(),violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 辅助方法，转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath message>
     */
    private static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e){
        return extractPropertyAndMessageAsList(e.getConstraintViolations()," ");
    }

    private static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations) {
        return extractPropertyAndMessageAsList(constraintViolations,"");
    }


    private static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(),separator);
    }

    private static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations, String separator) {
        List<String> errorMessages=new ArrayList<>();
        for(ConstraintViolation violation:constraintViolations){
            errorMessages.add(violation.getPropertyPath()+separator+violation.getMessage());
        }
        return errorMessages;
    }

    public static String validator(Object object,Class<?>...groups){
        try {
            validateWithException(validator,object,groups);
        }catch (ConstraintViolationException ex){
            List<String> list=extractMessage(ex);
            list.add(0,"数据验证失败");

            //封装错误信息为字符串
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<list.size();i++){
                String exMsg=list.get(i);
                if(i!=0){
                    sb.append(String.format("%s.%s",i,exMsg)).append(list.size()>1?"<br/>":"");
                }else {
                    sb.append(exMsg).append(list.size()>1?"<br/>":"");
                }
            }
            return sb.toString();
        }
        return null;

    }

}
