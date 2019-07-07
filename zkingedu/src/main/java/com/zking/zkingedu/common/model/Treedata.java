package com.zking.zkingedu.common.model;

        import lombok.Data;
        import org.springframework.stereotype.Component;

        import java.io.Serializable;
        import java.util.List;

@Data
@Component
public class Treedata implements Serializable {
    private static final long serialVersionUID = 5792188245200265113L;
    private Integer id;//节点唯一索引，用于对指定节点进行各类操作
    private String title;//节点标题
    private Boolean checked=false;//节点是否初始为选中状态（如果开启复选框的话,默认false
    private List<Treedata> children;//子节点。支持设定选项同父节点
}
