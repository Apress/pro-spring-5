package com.apress.prospring4.ch14;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class ListScriptEngines {
    public static void main(String[] args) {
        ScriptEngineManager mgr = new ScriptEngineManager();

        for (ScriptEngineFactory factory : mgr.getEngineFactories()) {
            String engineName= factory.getEngineName();
            String languageName = factory.getLanguageName();
            String version = factory.getLanguageVersion();
            System.out.println("Engine name: " + engineName + " Language: " + languageName  +
                    " Version: " + version);
        }
    }
}
