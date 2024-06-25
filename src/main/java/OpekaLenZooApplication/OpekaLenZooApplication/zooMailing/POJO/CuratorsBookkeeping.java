package OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.POJO;

import OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.ENUM.StatusCurator;

import java.io.File;

public record CuratorsBookkeeping(File curator, String bookkeeping, String mailAddress, StatusCurator status) {
}
