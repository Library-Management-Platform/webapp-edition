/* package com.platform.libraryManager.config;

import com.platform.libraryManager.enums.ResourceCategoryEnum;
import com.platform.libraryManager.enums.ResourceTypeEnum;
import com.platform.libraryManager.enums.ResourceStatusEnum;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.models.Loan;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.repositories.ClientRepository;
import com.platform.libraryManager.repositories.LibraryRepository;
import com.platform.libraryManager.repositories.LoanRepository;
import com.platform.libraryManager.repositories.ResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {

    private final LibraryRepository libraryRepository;
    private final ResourceRepository resourceRepository;
    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;
    private final Environment env;
    private final JdbcTemplate jdbcTemplate;

    public DataSeeder(
            LibraryRepository libraryRepository,
            ResourceRepository resourceRepository,
            ClientRepository clientRepository,
            LoanRepository loanRepository,
            Environment env,
            JdbcTemplate jdbcTemplate
    ) {
        this.libraryRepository = libraryRepository;
        this.resourceRepository = resourceRepository;
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
        this.env = env;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        boolean force = false;
        try {
            String prop = env.getProperty("app.seed.force");
            if (prop != null && prop.equalsIgnoreCase("true")) {
                force = true;
            }
        } catch (Exception ignored) {}

        String envForce = System.getenv("FORCE_SEED");
        if (envForce != null && envForce.equalsIgnoreCase("true")) {
            force = true;
        }

        if (!force && libraryRepository.count() > 0) {
            System.out.println("DataSeeder: Libraries already exist, skipping seeding.");
            return;
        }

        if (force) {
            System.out.println("DataSeeder: FORCE seeding enabled — clearing existing data via JDBC...");
            // Delete in order to respect FK constraints (children first)
            jdbcTemplate.execute("DELETE FROM loans");
            jdbcTemplate.execute("DELETE FROM email_verification_links");
            jdbcTemplate.execute("DELETE FROM resources");
            // remove librarians (references libraries)
            jdbcTemplate.execute("DELETE FROM librarians");
            // remove clients and admins (subtypes of users)
            jdbcTemplate.execute("DELETE FROM clients");
            jdbcTemplate.execute("DELETE FROM admins");
            // now libraries and users
            jdbcTemplate.execute("DELETE FROM libraries");
            jdbcTemplate.execute("DELETE FROM users");
        }

        System.out.println("DataSeeder: Starting DB seed...");

        LocalDateTime now = LocalDateTime.now();

        // Create some test clients
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Client c = new Client(
                    "client" + i,
                    "client" + i + "@example.com",
                    "password",
                    now,
                    now
            );
            clientRepository.save(c);
            clients.add(c);
        }

        Random rnd = new Random(1234);
        ResourceCategoryEnum[] categories = ResourceCategoryEnum.values();
        ResourceTypeEnum[] types = ResourceTypeEnum.values();

        // Create 10 libraries, each with 10 resources and 10 loans
        for (int libIdx = 1; libIdx <= 10; libIdx++) {
            Library lib = new Library(
                    "Library " + libIdx,
                    "Address " + libIdx,
                    now,
                    now
            );
            libraryRepository.save(lib);

            for (int rIdx = 1; rIdx <= 10; rIdx++) {
                ResourceCategoryEnum cat = categories[rnd.nextInt(categories.length)];
                ResourceTypeEnum typ = types[rnd.nextInt(types.length)];

                Resource res = new Resource(
                        lib,
                        "Resource L" + libIdx + "-R" + rIdx,
                        "Author " + rIdx,
                        cat,
                        typ
                );
                resourceRepository.save(res);

                // pick a client
                Client client = clients.get(rnd.nextInt(clients.size()));

                Loan loan = new Loan(client, res, lib);
                // Decide loan status variation
                int mode = rnd.nextInt(4); // 0..3
                switch (mode) {
                    case 0:
                        // RESERVED (leave as-is)
                        res.setStatus(ResourceStatusEnum.RESERVED);
                        break;
                    case 1:
                        // IN_PROGRESS
                        loan.markAsBorrowed(14);
                        res.setStatus(ResourceStatusEnum.BORROWED);
                        break;
                    case 2:
                        // RETURNED
                        loan.markAsBorrowed(14);
                        loan.markAsReturned();
                        res.setStatus(ResourceStatusEnum.AVAILABLE);
                        break;
                    case 3:
                        // CLOSED
                        loan.markAsBorrowed(14);
                        loan.markAsReturned();
                        loan.closeLoan(4, "Good read");
                        res.setStatus(ResourceStatusEnum.AVAILABLE);
                        break;
                    default:
                        res.setStatus(ResourceStatusEnum.AVAILABLE);
                }

                // Persist updated resource and loan
                resourceRepository.save(res);
                loanRepository.save(loan);
            }

            System.out.println("DataSeeder: Seeded " + lib.getName());
        }

        System.out.println("DataSeeder: DB seed complete.");
    }
}
 */