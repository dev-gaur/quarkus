package org.jboss.protean.arc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMember;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanAttributes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Decorator;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.InjectionTargetFactory;
import javax.enterprise.inject.spi.InterceptionFactory;
import javax.enterprise.inject.spi.InterceptionType;
import javax.enterprise.inject.spi.Interceptor;
import javax.enterprise.inject.spi.ObserverMethod;
import javax.enterprise.inject.spi.ProducerFactory;

/**
 *
 * @author Martin Kouba
 */
public class BeanManagerImpl implements BeanManager {

    static final LazyValue<BeanManagerImpl> INSTANCE = new LazyValue<>(BeanManagerImpl::new);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx) {
        Objects.requireNonNull(bean);
        Objects.requireNonNull(ctx);
        if (bean instanceof InjectableBean && ctx instanceof CreationalContextImpl) {
            return ArcContainerImpl.instance().beanInstanceHandle((InjectableBean) bean, (CreationalContextImpl) ctx).get();
        }
        throw new IllegalArgumentException(
                "Arguments must be instances of " + InjectableBean.class + " and " + CreationalContextImpl.class + ": \nbean: " + bean + "\nctx: " + ctx);
    }

    @Override
    public Object getInjectableReference(InjectionPoint ij, CreationalContext<?> ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual) {
        return new CreationalContextImpl<>();
    }

    @Override
    public Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers) {
        Objects.requireNonNull(beanType);
        return ArcContainerImpl.instance().getBeans(beanType, qualifiers);
    }

    @Override
    public Set<Bean<?>> getBeans(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bean<?> getPassivationCapableBean(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans) {
        return ArcContainerImpl.instance().resolve(beans);
    }

    @Override
    public void validate(InjectionPoint injectionPoint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fireEvent(Object event, Annotation... qualifiers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T event, Annotation... qualifiers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Decorator<?>> resolveDecorators(Set<Type> types, Annotation... qualifiers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Interceptor<?>> resolveInterceptors(InterceptionType type, Annotation... interceptorBindings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isScope(Class<? extends Annotation> annotationType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNormalScope(Class<? extends Annotation> annotationType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPassivatingScope(Class<? extends Annotation> annotationType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isQualifier(Class<? extends Annotation> annotationType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInterceptorBinding(Class<? extends Annotation> annotationType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isStereotype(Class<? extends Annotation> annotationType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Annotation> getInterceptorBindingDefinition(Class<? extends Annotation> bindingType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> stereotype) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean areQualifiersEquivalent(Annotation qualifier1, Annotation qualifier2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean areInterceptorBindingsEquivalent(Annotation interceptorBinding1, Annotation interceptorBinding2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getQualifierHashCode(Annotation qualifier) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInterceptorBindingHashCode(Annotation interceptorBinding) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Context getContext(Class<? extends Annotation> scopeType) {
        return Arc.container().getContext(scopeType);
    }

    @Override
    public ELResolver getELResolver() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> InjectionTargetFactory<T> getInjectionTargetFactory(AnnotatedType<T> annotatedType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <X> ProducerFactory<X> getProducerFactory(AnnotatedField<? super X> field, Bean<X> declaringBean) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <X> ProducerFactory<X> getProducerFactory(AnnotatedMethod<? super X> method, Bean<X> declaringBean) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> BeanAttributes<T> createBeanAttributes(AnnotatedType<T> type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BeanAttributes<?> createBeanAttributes(AnnotatedMember<?> type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Bean<T> createBean(BeanAttributes<T> attributes, Class<T> beanClass, InjectionTargetFactory<T> injectionTargetFactory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T, X> Bean<T> createBean(BeanAttributes<T> attributes, Class<X> beanClass, ProducerFactory<X> producerFactory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InjectionPoint createInjectionPoint(AnnotatedField<?> field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public InjectionPoint createInjectionPoint(AnnotatedParameter<?> parameter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Extension> T getExtension(Class<T> extensionClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> InterceptionFactory<T> createInterceptionFactory(CreationalContext<T> ctx, Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Event<Object> getEvent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Instance<Object> createInstance() {
        throw new UnsupportedOperationException();
    }

}