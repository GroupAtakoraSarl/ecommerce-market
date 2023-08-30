

package com.atakora.ecommercemarket.entities;

        import jakarta.persistence.*;
        import lombok.Data;
        import java.util.Date;
        import java.util.List;

@Entity
@Data
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
    private List<CommandItem> items;
}
