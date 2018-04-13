package expression.types;

import java.lang.reflect.Type;

public abstract class AbstractParsingType<T> implements ParsingType<T> {
  public Type getType() {
    return this.getClass().getGenericSuperclass();
  }
}
