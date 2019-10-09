/*
 * Copyright 2019 Web3 Labs LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.console.project.unit.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import org.web3j.console.project.ProjectCreator;
import org.web3j.console.project.utills.ClassExecutor;

import static java.io.File.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratorTest extends ClassExecutor {
    @TempDir static File temp;

    @Test
    public void testThatUnitClassWasGenerated()
            throws IOException, InterruptedException, ClassNotFoundException {
        final String[] args = {"new"};
        Process process =
                executeClassAsSubProcessAndReturnProcess(
                                ProjectCreator.class, Collections.emptyList(), Arrays.asList(args))
                        .start();
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        writer.write("Test", 0, "Test".length());
        writer.newLine();
        writer.write("org.com", 0, "org.com".length());
        writer.newLine();
        writer.write(temp.getPath(), 0, temp.getPath().length());
        writer.newLine();
        writer.close();
        process.waitFor();

        String[] genArgs = {"generate", temp + separator + "Test"};
        Generator.main(genArgs);
        assertTrue(
                new File(
                                temp
                                        + separator
                                        + "Test"
                                        + separator
                                        + "src"
                                        + separator
                                        + "test"
                                        + separator
                                        + "solidity"
                                        + separator
                                        + "org"
                                        + separator
                                        + "com"
                                        + separator
                                        + "generated"
                                        + separator
                                        + "contracts"
                                        + separator
                                        + "GreeterTest.java")
                        .exists());
    }
}
