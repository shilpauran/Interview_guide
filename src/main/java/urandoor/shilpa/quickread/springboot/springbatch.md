# **Spring Batch - Complete Guide**
Spring Batch is a framework for processing **large volumes of data** in a **batch job**. It is widely used for:
- **ETL (Extract, Transform, Load) processes**
- **Data migration**
- **Report generation**
- **Scheduled jobs (e.g., processing millions of records)**

---

## **🔹 1. Key Features of Spring Batch**
✅ **Chunk Processing** – Processes data in small chunks for efficiency.  
✅ **Step-Oriented Processing** – Defines batch jobs as multiple **steps**.  
✅ **Transaction Management** – Ensures rollback on failure.  
✅ **Parallel Processing** – Supports **multi-threading** and **partitioning**.  
✅ **Job Scheduling** – Can be integrated with **Quartz Scheduler** or **Spring Scheduler**.

---

## **🔹 2. Spring Batch Architecture**
A **Spring Batch job** consists of:
- **Job** → A complete batch process.
- **Step** → A phase of the batch job (e.g., Read → Process → Write).
- **ItemReader** → Reads input data (CSV, DB, XML, JSON).
- **ItemProcessor** → Transforms or validates the data.
- **ItemWriter** → Writes output data (DB, file, etc.).
- **JobRepository** → Stores job execution metadata.
- **JobLauncher** → Triggers the batch job.

---

## **🔹 3. Setting Up Spring Batch**
### **📌 Add Dependencies (`pom.xml`)**
```xml
<dependencies>
    <!-- Spring Boot Starter Batch -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-batch</artifactId>
    </dependency>

    <!-- H2 Database (for JobRepository) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

---

## **🔹 4. Configuring Spring Batch**
### **📌 Enable Spring Batch (`BatchConfig.java`)**
```java
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
}
```
- `@EnableBatchProcessing` initializes the batch framework.
- `@Configuration` marks it as a config class.

---

## **🔹 5. Creating a Simple Batch Job**
We’ll create a **Spring Batch Job** that:
1. **Reads Data** from a CSV file.
2. **Processes** it by converting names to uppercase.
3. **Writes** the processed data to the database.

---

### **📌 Step 1: Define Entity (`User.java`)**
```java
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Getters and Setters
}
```

---

### **📌 Step 2: Create a CSV Reader (`UserItemReader.java`)**
```java
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class UserItemReader {

    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("users.csv"));
        reader.setLinesToSkip(1); // Skip header

        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("name", "email");

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }
}
```
- **Reads users from `users.csv`**.
- **Maps CSV columns (`name, email`) to `User` entity**.

---

### **📌 Step 3: Create a Processor (`UserProcessor.java`)**
```java
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor implements ItemProcessor<User, User> {
    
    @Override
    public User process(User user) {
        user.setName(user.getName().toUpperCase());
        return user;
    }
}
```
- **Converts `name` to uppercase**.

---

### **📌 Step 4: Create a Database Writer (`UserItemWriter.java`)**
```java
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserItemWriter {

    @Bean
    public JdbcBatchItemWriter<User> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO users (name, email) VALUES (:name, :email)")
                .dataSource(dataSource)
                .build();
    }
}
```
- **Writes the processed users to a database**.

---

### **📌 Step 5: Configure Batch Job (`BatchJobConfig.java`)**
```java
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJobConfig {

    @Bean
    public Job importUserJob(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, UserItemReader reader, 
                     UserProcessor processor, JdbcBatchItemWriter<User> writer) {
        return stepBuilderFactory.get("step")
                .<User, User>chunk(5)
                .reader(reader.reader())
                .processor(processor)
                .writer(writer)
                .build();
    }
}
```
- Defines a **batch job (`importUserJob`)**.
- **Step: Reads → Processes → Writes users in chunks of 5**.

---

## **🔹 6. Running the Batch Job**
### **📌 Manually Trigger Batch (`BatchRunner.java`)**
```java
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobLauncher;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job importUserJob;

    public BatchRunner(JobLauncher jobLauncher, Job importUserJob) {
        this.jobLauncher = jobLauncher;
        this.importUserJob = importUserJob;
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(importUserJob, params);
    }
}
```
- **Runs the batch job on application startup**.

---

## **🔹 7. Running and Testing**
### **📌 Add `users.csv` File (src/main/resources/users.csv)**
```
name,email
Shilpa,shilpa@example.com
Amit,amit@example.com
Rohan,rohan@example.com
```

### **📌 Start the Application**
Run the Spring Boot application. It will:
1. Read users from **CSV**.
2. Convert **names to uppercase**.
3. Insert them into **database**.

### **📌 Verify in H2 Console**
- Visit **`http://localhost:8080/h2-console`**.
- Query:
  ```sql
  SELECT * FROM users;
  ```

---

## **🔹 8. Summary**
✅ **Spring Batch processes large data efficiently**.  
✅ **Uses `ItemReader`, `ItemProcessor`, `ItemWriter` to read → process → write data**.  
✅ **Supports database, CSV, XML, and JSON file processing**.  
✅ **Can be scheduled using Quartz or Spring Scheduler**.

Would you like to see **parallel processing or scheduling** next? 🚀