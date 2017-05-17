/**参数类*/
public class Parameters {
	public static int 		MAXSPLITLEN 		= 15; // 字符串截取的最大长度
	public static float[] 	LINEPOWER 			= {0.41f, 0.82f, 0.99f}; // 不同线段长度对应的权值 ，分为1、2和大于2三类
	public static float 	LENGTHEQUALPOWER 	= 0.1f; // 比对字符串长度是否相等的权值
	public static float		NUMBERPOWER			= 0.48f; // 字符中门牌号的权值
}
