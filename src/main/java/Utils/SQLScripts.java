package Utils;

public class SQLScripts {

public static final String existByEmailScript = """
        
        SELECT * FROM users WHERE email = ? LIMIT 1
        
        """;

public static final String saveUserScript = """
        
        INSERT INTO users (first_name, last_name, email, passwrd, user_role, age, company_name)
            VALUES (?,?,?,?,?,?,?)
        
        """;

public static final String findByEmailScript = """
        
        SELECT * FROM users WHERE email = ?
        
        """;
public static final String listAllVehiclesScript = """
        
        SELECT v.id AS id,
            v.brand AS brand,
            v.model AS model,
            vt.id AS vehicle_type_id,
            vt.vehicle_type AS vehicle_type,
            vt.hourly_rental_fee AS hourly_rental_fee,
            vt.daily_rental_fee AS daily_rental_fee,
            vt.weekly_rental_fee AS weekly_rental_fee,
            vt.monthly_rental_fee AS monthly_rental_fee
        
            FROM vehicles v
            JOIN vehicle_type vt ON v.vehicle_type_id = vt.id
            ORDER BY id ASC
            LIMIT ? OFFSET ?;
        
        """;
public static final String totalPageCountScript = """

            SELECT COUNT(*) FROM vehicles

        """;

public static final  String createVehicleScript = """
        
        INSERT INTO vehicles (brand, model, vehicle_type_id, price)
        VALUES (?,?,?,?)
        
        """;

public static final String searchByBrandScript = """
        
        SELECT v.id AS id,
            v.brand AS brand,
            v.model AS model,
            vt.id AS vehicle_type_id,
            vt.vehicle_type AS vehicle_type,
            vt.hourly_rental_fee AS hourly_rental_fee,
            vt.daily_rental_fee AS daily_rental_fee,
            vt.weekly_rental_fee AS weekly_rental_fee,
            vt.monthly_rental_fee AS monthly_rental_fee
        
            FROM vehicles v
            JOIN vehicle_type vt ON v.vehicle_type_id = vt.id
            WHERE brand ILIKE ?
            ORDER BY id ASC
            LIMIT ? OFFSET ?;
        
        """;
public static final String searchByTypeScript = """
        
        SELECT v.id AS id,
            v.brand AS brand,
            v.model AS model,
            vt.id AS vehicle_type_id,
            vt.vehicle_type AS vehicle_type,
            vt.hourly_rental_fee AS hourly_rental_fee,
            vt.daily_rental_fee AS daily_rental_fee,
            vt.weekly_rental_fee AS weekly_rental_fee,
            vt.monthly_rental_fee AS monthly_rental_fee
        
            FROM vehicles v
            JOIN vehicle_type vt ON v.vehicle_type_id = vt.id
            WHERE vehicle_type_id = ?
            ORDER BY id ASC
            LIMIT ? OFFSET ?;
        
        
        """;
public static final String searchByPriceRange = """
        
        SELECT v.id AS id,
            v.brand AS brand,
            v.model AS model,
            vt.id AS vehicle_type_id,
            vt.vehicle_type AS vehicle_type,
            vt.hourly_rental_fee AS hourly_rental_fee,
            vt.daily_rental_fee AS daily_rental_fee,
            vt.weekly_rental_fee AS weekly_rental_fee,
            vt.monthly_rental_fee AS monthly_rental_fee
        
            FROM vehicles v
            JOIN vehicle_type vt ON v.vehicle_type_id = vt.id
            WHERE price BETWEEN ? AND ?
            ORDER BY id ASC
            LIMIT ? OFFSET ?;
        
        """;
    public static final String findByVehicleIDScript = """
            
            SELECT v.id AS id,
            v.brand AS brand,
            v.model AS model,
            vt.id AS vehicle_type_id,
            vt.vehicle_type AS vehicle_type,
            v.price AS price,
            vt.hourly_rental_fee AS hourly_rental_fee,
            vt.daily_rental_fee AS daily_rental_fee,
            vt.weekly_rental_fee AS weekly_rental_fee,
            vt.monthly_rental_fee AS monthly_rental_fee
        
            FROM vehicles v
            JOIN vehicle_type vt ON v.vehicle_type_id = vt.id
            WHERE v.id = ?
            ORDER BY id ASC
            ;
            
            """;
    public static final String saveOrderScript = """
            
            INSERT INTO orders (user_id, vehicle_id, total_price, status, deposit, rental_type, duration, start_date, end_date)
            
            VALUES (?,?,?,?,?,?,?,?,?)
            RETURNING order_id;
            
            
            """;
    public static final String savePaymentScript = """
            
            INSERT INTO payment (order_id, amount, deposit, status)
            
            VALUES (?,?,?,?)
            
            """;
    public static final String isVehicleAvailableScript = """
            
            SELECT COUNT(*)
            FROM orders
            WHERE vehicle_id = ?
              AND start_date <= ?
              AND end_date   >= ?;
            
            """;
    public static final String listAllOrdersScript = """
            
            SELECT order_id,
            brand,
            model,
            start_date,
            end_date,
            status,
            deposit,
            total_price
            FROM orders
            JOIN vehicles ON vehicles.id = orders.vehicle_id
            
            WHERE orders.user_id = ?
            LIMIT ? OFFSET ?;
            
            """;

    public static final String updateVehicleBrandScript = """
            
            UPDATE vehicles SET brand = ? WHERE id = ?;
            
            """;

    public static final String updateVehicleModelScript = """
            
            UPDATE vehicles SET model = ? WHERE id = ?;
            
            """;

    public static final String updateVehiclePriceScript = """
            
            UPDATE vehicles SET price = ? WHERE id = ?;
            
            """;

    public static final String updateVehicleTypeIdScript = """
            
            UPDATE vehicles SET vehicle_type_id = ? WHERE id = ?;
            
            """;

    public static String deleteVehicleScript = """
            
            DELETE FROM vehicles
            WHERE id = ?
            
            """;
}
