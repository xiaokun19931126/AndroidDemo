package com.example;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * @author xiaokun
 * @date 2017/12/6
 */
@AutoService(Processor.class)    //帮我们生成 META-INF 信息
@SupportedAnnotationTypes("com.example.BindView")    //要处理的注解类型
@SupportedSourceVersion(SourceVersion.RELEASE_7)    //支持的源码版本
public class BindViewProcessor extends AbstractProcessor
{

    private Messager mMessager;
    //元素操作类
    private Elements mElementUtils;
    //文件创建者
    private Filer mFileCreator;

    private Map<String, ProxyInfo> mProxyMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment)
    {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
        mElementUtils = processingEnvironment.getElementUtils();
        mFileCreator = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment)
    {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "process...");
        mProxyMap.clear();
        //获取被BindView注解的元素,,,就是成员变量比如textview什么的
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //1.搜集信息
        for (Element element : elements)
        {
            if (!checkAnnotationValid(element, BindView.class))
            {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            String qualifiedName = typeElement.getQualifiedName().toString();

            ProxyInfo proxyInfo = mProxyMap.get(qualifiedName);
            if (proxyInfo == null)
            {
                proxyInfo = new ProxyInfo(mElementUtils, typeElement);
                mProxyMap.put(qualifiedName, proxyInfo);
            }

            BindView annotation = variableElement.getAnnotation(BindView.class);
            if (annotation != null)
            {
                int valueId = annotation.value();
                proxyInfo.mInjectElements.put(valueId, variableElement);
            }

            //2.生成代理类
            for (String key : mProxyMap.keySet())
            {
                ProxyInfo info = mProxyMap.get(key);
                try
                {
                    JavaFileObject sourceFile = mFileCreator.createSourceFile(info.getProxyClassFullName(),
                            info.getTypeElement());
                    Writer writer = sourceFile.openWriter();
                    writer.write(info.generateJavaCode());
                    writer.flush();
                    writer.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                    error(proxyInfo.getTypeElement(), "Unable to write injector for type %s: %s", proxyInfo.getTypeElement(), e.getMessage());
                }
            }
        }
        return true;
    }

    /**
     * 检测element类型是否规范  包括不为空，是Field类型  必须是public修饰
     * Bindview只能被注解在field上
     *
     * @param element
     * @param clazz
     * @return
     */
    private boolean checkAnnotationValid(final Element element, final Class<?> clazz)
    {
        if (element == null || element.getKind() != ElementKind.FIELD)
        {
            error(element, "%s must be declared on field !", clazz.getSimpleName());
            return false;
        }
        if (!element.getModifiers().contains(Modifier.PUBLIC))
        {
            error(element, "%s must be public !", element.getSimpleName());
            return false;
        }
        return true;
    }

    /**
     * 错误信息
     *
     * @param element
     * @param msg
     * @param args
     */
    private void error(final Element element, String msg, final Object... args)
    {
        if (args != null && args.length > 0)
        {
            msg = String.format(msg, args);
            mMessager.printMessage(Diagnostic.Kind.ERROR, msg, element);
        }
    }

}
