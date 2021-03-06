package org.gumplab.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user") //表名与实体名一样可不用配置该注解
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false) // nullable=false 在内部将nullable = false转换为@NotNull
    private String name;

    @Column(nullable=false) 
    private String password;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String idcard;

    @Column(nullable=false)
    private String sex;

    /**
     * GenerationType.TABLE : 使用一个特定的数据库表格来保存主键。
     * GenerationType.AUTO : 根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * GenerationType.IDENTITY : 主键由数据库自动生成（主要是自动增长型）
     * 不支持GenerationType.SEQUENCE : 主键由程序控制(也是默认的,在指定主键时，如果不指定主键生成策略，默认为AUTO)
     */
}
