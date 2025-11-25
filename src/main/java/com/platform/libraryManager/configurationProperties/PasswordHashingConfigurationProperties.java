package com.platform.libraryManager.configurationProperties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "spring.security.password.argon2")
public class PasswordHashingConfigurationProperties {

    private int saltLength;
    private int hashLength;
    private int parallelism;
    private int memory;
    private int iterations;

    public int getSaltLength() { return saltLength; }
    public void setSaltLength(int saltLength) { this.saltLength = saltLength; }

    public int getHashLength() { return hashLength; }
    public void setHashLength(int hashLength) { this.hashLength = hashLength; }

    public int getParallelism() { return parallelism; }
    public void setParallelism(int parallelism) { this.parallelism = parallelism; }

    public int getMemory() { return memory; }
    public void setMemory(int memory) { this.memory = memory; }

    public int getIterations() { return iterations; }
    public void setIterations(int iterations) { this.iterations = iterations; }

}
