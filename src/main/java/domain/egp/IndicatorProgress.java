/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.egp;

import com.dai.tamislogin.TamisLogin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.TAMISUtil;

/**
 *
 * @author jDeRiggi
 */
public class IndicatorProgress {

    private final static Logger log = Logger.getLogger(IndicatorProgress.class.getName());
    private float baseline, fy1Target, fy1Q1, fy1q1, fy1q2, fy1q3, fy1q4, fy1total;
    private float fy2Target, fy2Q1, fy2q1, fy2q2, fy2q3, fy2q4, fy2total;
    private String indicator;

    private enum TableKey {

        indicator, baseline, fy1target, fy1q1, q2, q3, q4, fy1total, fy2target, fy2q1, q2_, q3_, q4_, fy2total
    }

    /**
     * Populate this object with the raw data
     *
     * @param rawData
     */
    public void populate(HashMap<String, String> row) {
        for (TableKey key : TableKey.values()) {
            set(key, safeGet(row, key));
        }
    }

    
    private String safeGet(HashMap<String, String> row, TableKey key) {
        if (row == null || key == null) {
            log.log(Level.WARNING, "row or key is null");

            return null;
        }

        if (!row.containsKey(key.toString())) {
            log.log(Level.WARNING, "row does not have column named {0} ", key);
            return null;
        }


        return row.get(key.toString());
    }

    private void set(TableKey key, String value) {
        switch (key) {
            case baseline:
                setBaseline(TAMISUtil.cleanAndParseFloat(value));
                break;
            case indicator:
                setIndicator(value);
                break;
            case fy1target:
                setFy1Target(TAMISUtil.cleanAndParseFloat(value));
                break;
            case fy1q1:
                setFy1Q1(TAMISUtil.cleanAndParseFloat(value));
                break;
            case q2:
                setFy1q2(TAMISUtil.cleanAndParseFloat(value));
                break;
            case q3:
                setFy1q3(TAMISUtil.cleanAndParseFloat(value));
                break;
            case q4:
                setFy1q4(TAMISUtil.cleanAndParseFloat(value));
                break;
            case fy1total:
                setFy1total(TAMISUtil.cleanAndParseFloat(value));
                break;
            case fy2target:
                setFy2Target(TAMISUtil.cleanAndParseFloat(value));
                break;
            case fy2q1:
                setFy2q1(TAMISUtil.cleanAndParseFloat(value));
                break;
            case q2_:
                setFy2q2(TAMISUtil.cleanAndParseFloat(value));
                break;
            case q3_:
                setFy2q3(TAMISUtil.cleanAndParseFloat(value));
                break;
            case q4_:
                setFy2q4(TAMISUtil.cleanAndParseFloat(value));
                break;
            case fy2total:
                setFy2total(TAMISUtil.cleanAndParseFloat(value));
                break;
        }

    }

    public float getBaseline() {
        return baseline;
    }


    public void setBaseline(float baseline) {
        this.baseline = baseline;
    }

    public float getFy1Target() {
        return fy1Target;
    }

    public void setFy1Target(float fy1Target) {
        this.fy1Target = fy1Target;
    }

    public float getFy1Q1() {
        return fy1Q1;
    }

    public void setFy1Q1(float fy1Q1) {
        this.fy1Q1 = fy1Q1;
    }

    public float getFy1q1() {
        return fy1q1;
    }

    public void setFy1q1(float fy1q1) {
        this.fy1q1 = fy1q1;
    }

    public float getFy1q2() {
        return fy1q2;
    }

    public void setFy1q2(float fy1q2) {
        this.fy1q2 = fy1q2;
    }

    public float getFy1q3() {
        return fy1q3;
    }

    public void setFy1q3(float fy1q3) {
        this.fy1q3 = fy1q3;
    }

    public float getFy1q4() {
        return fy1q4;
    }

    public void setFy1q4(float fy1q4) {
        this.fy1q4 = fy1q4;
    }

    public float getFy1total() {
        return fy1total;
    }

    public void setFy1total(float fy1total) {
        this.fy1total = fy1total;
    }

    public float getFy2Target() {
        return fy2Target;
    }

    public void setFy2Target(float fy2Target) {
        this.fy2Target = fy2Target;
    }

    public float getFy2Q1() {
        return fy2Q1;
    }

    public void setFy2Q1(float fy2Q1) {
        this.fy2Q1 = fy2Q1;
    }

    public float getFy2q1() {
        return fy2q1;
    }

    public void setFy2q1(float fy2q1) {
        this.fy2q1 = fy2q1;
    }

    public float getFy2q2() {
        return fy2q2;
    }

    public void setFy2q2(float fy2q2) {
        this.fy2q2 = fy2q2;
    }

    public float getFy2q3() {
        return fy2q3;
    }

    public void setFy2q3(float fy2q3) {
        this.fy2q3 = fy2q3;
    }

    public float getFy2q4() {
        return fy2q4;
    }

    public void setFy2q4(float fy2q4) {
        this.fy2q4 = fy2q4;
    }

    public float getFy2total() {
        return fy2total;
    }

    public void setFy2total(float fy2total) {
        this.fy2total = fy2total;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String inidicator) {
        this.indicator = inidicator;
    }
    
    @Override
    public String toString(){
        return this.indicator;
    }
}
