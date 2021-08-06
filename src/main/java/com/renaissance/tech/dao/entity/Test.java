package com.renaissance.tech.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "test")
public class Test {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "`name`")
    private String name;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";
}