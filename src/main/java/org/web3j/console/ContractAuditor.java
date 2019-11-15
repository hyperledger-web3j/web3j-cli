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
package org.web3j.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.web3j.codegen.Console.exitError;

public class ContractAuditor {

    private static final String USAGE = "audit <file name>";

    public static void main(String[] args) {

        if (args.length != 1) {
            exitError(USAGE);
        }
        try {
            List<String> originalArgs = new ArrayList<>();
            originalArgs.add("-p");
            originalArgs.addAll(Arrays.asList(args));
            ru.smartdec.smartcheck.app.cli.Tool.main(originalArgs.toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
