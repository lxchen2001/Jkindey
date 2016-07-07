package com.liji.jkidney.activity.check;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liji.jkidney.R;
import com.liji.jkidney.fragment.FragmentBase;
import com.liji.jkidney.model.CheckTypeId;
import com.liji.jkidney.model.check.MCheckType;
import com.liji.jkidney.utils.JTimeUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 */
public class FragmentCheckStatistics extends FragmentBase {

    private static String POS = "position";
    private static String DATA = "data";
    private static String TYPE = "type";

    List<MCheckType> checkTypeNiaodanbais = new ArrayList<>();
    int position = 0;
    int type = CheckTypeId.Gangongneng;

    @ViewInject(R.id.chart_bottom)
    ColumnChartView chartBottom;
    private ColumnChartData columnData;

    public FragmentCheckStatistics() {
    }


    public static FragmentCheckStatistics newInstance(List<MCheckType> checkTypeNiaodanbais, int position, int type) {
        FragmentCheckStatistics f = new FragmentCheckStatistics();
        Bundle b = new Bundle();
        b.putInt(POS, position);
        b.putInt(TYPE, type);
        b.putSerializable(DATA, (Serializable) checkTypeNiaodanbais);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = this.getArguments().getInt(POS);
        type = this.getArguments().getInt(TYPE);
        checkTypeNiaodanbais = (List<MCheckType>) this.getArguments().getSerializable(DATA);
    }


    @Override
    public View getOnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_statistics, container, false);
        x.view().inject(this, view);

        setData(checkTypeNiaodanbais);
//        chartBottom.setOnValueTouchListener(new ValueTouchListener());
        return view;
    }

    public void setData(List<MCheckType> data) {
        //每个x坐标显示的柱状图个数
        int numSubcolumns = 1;

        //每个检查项目中的检查次数
        int numColumns = data.size();

        //横坐标的数值
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;

        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();

            //随机生成每个月份的高度
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue(Float.valueOf(String.valueOf(data.get(i).getList().get(position).getValue())), ChartUtils.pickColor()));
            }

            //添加横坐标的数据
            axisValues.add(new AxisValue(i).setLabel(JTimeUtils.getDate(data.get(i).getList().get(position).getTime())));
            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        columnData = new ColumnChartData(columns);
        Axis axisX = new Axis(axisValues).setHasLines(true);
        axisX.setName("检查日期：月/日");
        columnData.setAxisXBottom(axisX);
        Axis axisY = new Axis().setHasLines(true).setMaxLabelChars(2);
        axisY.setName("\n检查结果");
        columnData.setAxisYLeft(axisY);

        chartBottom.setColumnChartData(columnData);
        // Set selection mode to keep selected month column highlighted.
        chartBottom.setValueSelectionEnabled(true);

        chartBottom.setZoomType(ZoomType.HORIZONTAL);

    }

//    private class ValueTouchListener implements ColumnChartOnValueSelectListener {
//
//        @Override
//        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
//
//            JToastUtils.showToast(getContext(), "" + value.getValue());
//        }
//
//        @Override
//        public void onValueDeselected() {
//
//        }
//
//    }


}
