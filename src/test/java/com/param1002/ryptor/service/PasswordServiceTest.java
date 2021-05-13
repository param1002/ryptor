package com.param1002.ryptor.service;

import com.param1002.ryptor.model.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    @InjectMocks
    private PasswordService target;

    @BeforeEach
    void setup() throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Password password = Password.builder().id("12345").name("abcd").value("secret").build();
        target.add(password);
    }

    @Test
    void shouldGetPasswords_WhenInvokedRetrieve() {

        final List<Password> passwords = target.retrieve();
        assertThat(passwords).isNotNull();
        assertThat(passwords).hasSize(1);
        assertThat(passwords.get(0).getId()).isEqualTo("12345");
        assertThat(passwords.get(0).getName()).isEqualTo("abcd");
        assertThat(passwords.get(0).getValue()).isEqualTo("secret");
    }

    @Test
    void shouldAddPasswordForGivenRequest() throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Password password = Password.builder().id("45678").name("efghi").value("secretKey").build();
        target.add(password);
        final Map<String, Password> passwords = target.retrieve().stream().collect(Collectors.toMap(Password::getId, Function.identity()));
        assertThat(passwords).isNotNull();
        assertThat(passwords).hasSize(2);
        assertThat(passwords.get("12345").getId()).isEqualTo("12345");
        assertThat(passwords.get("12345").getName()).isEqualTo("abcd");
        assertThat(passwords.get("12345").getValue()).isEqualTo("secret");

    }

    @Test
    void shouldUpdatePasswordsForGivenRequest() {

        final Password password = Password.builder().id("12345").name("efghi").value("secretKey").build();
        target.update(password);
        final List<Password> passwords = target.retrieve();
        assertThat(passwords).isNotNull();
        assertThat(passwords).hasSize(1);
        assertThat(passwords.get(0).getId()).isEqualTo("12345");
        assertThat(passwords.get(0).getName()).isEqualTo("efghi");
        assertThat(passwords.get(0).getValue()).isEqualTo("secretKey");
    }

    @Test
    void shouldDeletePasswordForGivenId() {

        target.delete("12345");
        assertThat(target.retrieve()).hasSize(0);
    }

}