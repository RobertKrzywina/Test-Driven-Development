package pl.robert.project.server.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Product {

        public static final int COL_LENGTH_NAME = 35;

        public static final Pattern PRODUCT_NAME_FORMAT_REGEX =
                Pattern.compile("^-?\\d+$", Pattern.CASE_INSENSITIVE);
    }
}
