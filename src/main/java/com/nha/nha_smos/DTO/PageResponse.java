package com.nha.nha_smos.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

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
}
