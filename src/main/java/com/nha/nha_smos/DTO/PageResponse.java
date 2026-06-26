package com.nha.nha_smos.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T>  content;
    private int  pageNumber;
    private int  pageSize;
    private int  totalPages;
    private long  totalElements;
    private boolean first;
    private boolean last;

//    public void setContent(List<T> content) {}
//    public String getContent(){
//        return content.toString();
//    }

//    response without mapping
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
//    create  overloading function response with mapping
//    E->Category, T->Response
    public static <E,T> PageResponse<T> from(Page<E> page, Function<E,T> mapper) {
        return new PageResponse<>(
                page.getContent().stream().map(mapper).toList(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}
