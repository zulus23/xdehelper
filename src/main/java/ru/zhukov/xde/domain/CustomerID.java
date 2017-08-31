package ru.zhukov.xde.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class CustomerID  implements Serializable{
    @Column(name = "cust_seq")
    private Integer seq;
    @Column(name = "cust_num")
    private String customerNumber;

    public CustomerID() {
    }

    public CustomerID(Integer seq, String customerNumber) {
        this.seq = seq;
        this.customerNumber = customerNumber;
    }

    public Integer getSeq() {
        return seq;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerID that = (CustomerID) o;

        if (!getSeq().equals(that.getSeq())) return false;
        return getCustomerNumber().equals(that.getCustomerNumber());
    }

    @Override
    public int hashCode() {
        int result = getSeq().hashCode();
        result = 31 * result + getCustomerNumber().hashCode();
        return result;
    }
}
