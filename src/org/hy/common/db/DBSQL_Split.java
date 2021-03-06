package org.hy.common.db;

import java.util.Map;

import org.hy.common.Help;
import org.hy.common.SplitSegment;
import org.hy.common.StringHelp;





/**
 * 分段SQL信息。
 * 
 * 通过 <[ ... ]> 分段的SQL
 * 
 * 同时解释占位符
 * 
 * @author      ZhengWei(HY)
 * @version     v1.0  
 *              v2.0  2014-07-30
 *              v3.0  2014-10-08  placeholders属性为有降序排序顺序的LinkedMap。
 *                                用于解决 :A、:AA 同时存在时的混乱。
 *              v4.0  2015-12-10  支持 :A.B.C 的解释（对点.的解释）。
 * @createDate  2012-10-30
 */
public class DBSQL_Split extends SplitSegment
{
    
    /**
     * 占位符信息的集合
     * 
     * placeholders属性为有降序排序顺序的LinkedMap。
     *   用于解决 :A、:AA 同时存在时的混乱。
     * 
     * Map.key    为占位符。前缀为:符号
     * Map.Value  为占位符原文本信息
     */
    private Map<String ,Object> placeholders;
    
    /**
     * 占位符信息的集合（保持占位符原顺序不变）
     * 
     * Map.key    为占位符。前缀为:符号
     * Map.Value  为占位符原文本信息
     */
    private Map<String ,Object> placeholdersSequence;
    
    
    
    public DBSQL_Split(SplitSegment i_SplitSegment)
    {
        super(i_SplitSegment);
    }
    
    
    
    public Map<String ,Object> getPlaceholders()
    {
        return placeholders;
    }
    
    
    
    public Map<String ,Object> getPlaceholdersSequence()
    {
        return placeholdersSequence;
    }
    
    
    
    /**
     * 解释占位符
     */
    public synchronized void parsePlaceholders()
    {
        if ( Help.isNull(this.info) )
        {
            return;
        }
        
        this.placeholdersSequence = StringHelp.parsePlaceholdersSequence(this.info);
        this.placeholders         = Help.toReverse(this.placeholdersSequence);
    }
    
    
    
    public int getPlaceholderSize()
    {
        if ( this.placeholders == null )
        {
            return 0;
        }
        else
        {
            return this.placeholders.size();
        }
    }
    
}
