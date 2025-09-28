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
                   vt.vehicle_type AS vehicle_type
            FROM vehicles v
            JOIN vehicle_type vt ON v.vehicle_type_id = vt.id
            ORDER BY id ASC
            LIMIT ? OFFSET ?;
        
        """;
public static final String totalPageCountScript = """

            SELECT COUNT(*) FROM vehicles

        """;

public static final  String createVehicleScript = """
        
        INSERT INTO vehicles (brand, model, vehicle_type, price)
        VALUES (?,?,?,?)
        
        """;

public static final String searchByBrandScript = """
        
        SELECT v.id AS id,
                   v.brand AS brand,
                   v.model AS model,
                   vt.id AS vehicle_type_id,
                   vt.vehicle_type AS vehicle_type

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
                   vt.vehicle_type AS vehicle_type

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
                   vt.vehicle_type AS vehicle_type

            FROM vehicles v
            JOIN vehicle_type vt ON v.vehicle_type_id = vt.id
            WHERE price BETWEEN ? AND ?
            ORDER BY id ASC
            LIMIT ? OFFSET ?;
        
        """;
    public static final String findByVehicleIDScript = """
            
            SELECT * FROM vehicles WHERE id = ?
            
            """;
    public static final String saveOrderScript = """
            
            INSERT INTO orders (user_id, vehicle_id, total_price, status, deposit, rental_type, duration, start_date, end_date)
            
            VALUES (?,?,?,?,?,?,?,?,?)
            
            
            """;
    public static final String savePaymentScript = """
            
            INSERT INTO payment (order_id, amount, deposit, status)
            
            VALUES (?,?,?,?)
            
            """;
}
