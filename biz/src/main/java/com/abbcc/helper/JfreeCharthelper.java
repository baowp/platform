package com.abbcc.helper;

import java.awt.BasicStroke;
import java.awt.Font;

import java.awt.Color;

import java.text.DecimalFormat;

import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import org.jfree.chart.labels.StandardPieToolTipGenerator;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.data.category.CategoryDataset;

import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.general.DefaultPieDataset;

public class JfreeCharthelper {
	/**
	 * title// 图表标题
	 * zh //目录轴的显示标签
	 * lh //数值轴的显示标签
	 * dataset // 数据集
	 */

	public static JFreeChart createBarChart(String title,String zh,String lh,CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart3D(

		title, // 图表标题

				zh, // 目录轴的显示标签(横坐标上的文字)

				lh, // 数值轴的显示标签(纵坐标上的文字)

				dataset, // 数据集

				PlotOrientation.VERTICAL, // 图表方向：水平、垂直

				true, // 是否显示图例(对于简单的柱状图必须是false)

				true, // 是否生成工具

				true // 是否生成URL链接

				);
		// 主要是为了解决乱码问题
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangeCrosshairVisible(true);
		categoryplot.setRangeCrosshairPaint(Color.blue);
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
		barrenderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 12));
		barrenderer.setSeriesItemLabelFont(1, new Font("宋体", Font.PLAIN, 12));
		CategoryAxis domainAxis = categoryplot.getDomainAxis();

		/*------设置X轴坐标上的文字-----------*/
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));

		/*------设置X轴的标题文字------------*/
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));

		/*------设置Y轴坐标上的文字-----------*/
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));

		/*------设置Y轴的标题文字------------*/
		numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));

		/*------这句代码解决了底部汉字乱码的问题-----------*/
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 12));
		return chart;

	}

	

	public static JFreeChart createPaiChart() {

		DefaultPieDataset data = getDataSet();

		JFreeChart chart = ChartFactory.createPieChart3D("水果产量图", // 图表标题

				data,

				true, // 是否显示图例

				false,

				false

		);

		PiePlot plot = (PiePlot) chart.getPlot();

		resetPiePlot(plot);
		plot.setLabelFont(new Font("Courier   New", Font.PLAIN, 12));
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 12));
		return chart;

	}

	private static void resetPiePlot(PiePlot plot) {

		String unitSytle = "{0}={1}({2})";

		plot.setNoDataMessage("无对应的数据，请重新查询。");

		plot.setNoDataMessagePaint(Color.red);

		// 指定 section 轮廓线的厚度(OutlinePaint不能为null)

		plot.setOutlineStroke(new BasicStroke(0));

		// 设置第一个 section 的开始位置，默认是12点钟方向

		plot.setStartAngle(90);

		plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle,

		NumberFormat.getNumberInstance(),

		new DecimalFormat("0.00%")));

		// 指定图片的透明度

		plot.setForegroundAlpha(0.65f);

		// 引出标签显示样式

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,

		NumberFormat.getNumberInstance(),

		new DecimalFormat("0.00%")));

		// 图例显示样式

		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				unitSytle,

				NumberFormat.getNumberInstance(),

				new DecimalFormat("0.00%")));

	}

	private static DefaultPieDataset getDataSet() {

		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("苹果", 100);

		dataset.setValue("梨子", 200);

		dataset.setValue("葡萄", 300);

		dataset.setValue("香蕉", 400);

		dataset.setValue("荔枝", 500);

		return dataset;

	}

}