package org.web3j.console.project.unit.gen.templates;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import java.util.List;
import javax.lang.model.element.Modifier;
import org.junit.jupiter.api.Test;

public class DeployTemplate extends UnitTemplate {
    private List<Class> deployArguments;
    private List<ParameterSpec> methodParameters;
    private Class contractName;

    public DeployTemplate(Class contractName, List<Class> deployArguments, List<ParameterSpec> methodParameters) {
        super(contractName,deployArguments,methodParameters);
        this.contractName = contractName;
        this.deployArguments = deployArguments;
        this.methodParameters = methodParameters;
    }

    public MethodSpec generate() {
        return MethodSpec.methodBuilder("testDeploy")
                .addAnnotation(Test.class)
                .addModifiers(Modifier.PUBLIC)
                .addException(Exception.class)
                .returns(TypeName.VOID)
                .addParameters(methodParameters)
                .addStatement("$T $L = $T.deploy(" + generatePattern() + ").send()", joined())
                .build();

    }

}

