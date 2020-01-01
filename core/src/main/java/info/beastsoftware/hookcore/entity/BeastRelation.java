package info.beastsoftware.hookcore.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class BeastRelation implements JSON {
    private final String name;
}
