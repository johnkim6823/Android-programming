# Android Programming Projects

> 안드로이드 앱 개발 실습을 위한 프로젝트 모음집
> A collection of Android projects for hands-on mobile app development practice.

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com)
[![API](https://img.shields.io/badge/Min%20API-21%20(Lollipop)-blue.svg)](https://developer.android.com/about/versions/lollipop)

---

## Overview

이 저장소는 안드로이드 앱 개발의 핵심 개념을 학습하기 위한 실습 프로젝트들을 포함하고 있습니다. 각 프로젝트는 독립적인 Android Studio 프로젝트로, UI 컴포넌트, 데이터베이스, 액티비티 생명주기 등 다양한 주제를 다룹니다.

## Project Structure

```
Android-programming/
├── Activity20220404/        # 액티비티 생명주기 & 화면 전환
├── Buttton20220307/         # 버튼 위젯 & 클릭 이벤트
├── Calculator20220314/      # 사칙연산 계산기
├── CompoundButton20220321/  # 체크박스, 토글, 스위치
├── Diary20220425/           # 간단 일기장 (DatePicker + 파일 I/O)
├── Girlgroup20220404/       # SQLite CRUD 데이터베이스
├── Login20220418/           # 로그인/회원가입 시스템
├── Pet20220321/             # 동물 선택 UI (RadioGroup + ImageView)
├── RecycleHelper/           # 분리수거 도우미 앱
└── Reservation20220328/     # 시간 예약 시스템 (Chronometer + CalendarView)
```

## Projects

### UI & Widget 기초

| 프로젝트 | 설명 | 핵심 학습 내용 |
|----------|------|----------------|
| **Buttton20220307** | 버튼 클릭 시 Toast 메시지 표시 | `Button`, `OnClickListener`, `Toast` |
| **CompoundButton20220321** | 복합 버튼 위젯 데모 | `ToggleButton`, `Switch`, `CheckBox` |
| **Pet20220321** | 동물 선택 후 이미지 표시 | `RadioGroup`, `CheckBox`, `ImageView`, 동적 UI 가시성 제어 |

### Activity & Navigation

| 프로젝트 | 설명 | 핵심 학습 내용 |
|----------|------|----------------|
| **Activity20220404** | 멀티 액티비티 화면 전환 | `Intent`, Activity 생명주기, 화면 네비게이션 |
| **Login20220418** | 로그인/회원가입 시스템 | SQLite 인증, `Intent` 데이터 전달, 다중 화면 |

### Data & Storage

| 프로젝트 | 설명 | 핵심 학습 내용 |
|----------|------|----------------|
| **Diary20220425** | 날짜별 일기 작성/저장/읽기 | `DatePicker`, 파일 I/O (`openFileOutput`/`openFileInput`) |
| **Girlgroup20220404** | 걸그룹 정보 CRUD 관리 | `SQLiteOpenHelper`, SQL 쿼리, `Cursor` |
| **Calculator20220314** | 사칙연산 계산기 | `EditText` 입력 처리, 숫자 파싱, 예외 처리 |

### Advanced

| 프로젝트 | 설명 | 핵심 학습 내용 |
|----------|------|----------------|
| **Reservation20220328** | 날짜/시간 선택 예약 시스템 | `CalendarView`, `TimePicker`, `Chronometer` |
| **RecycleHelper** | 분리수거 도우미 앱 | 로그인 시스템, 분리수거 가이드, 검색 기능 |

## Getting Started

### Prerequisites

- **Android Studio** Bumblebee (2021.1.1) 이상
- **JDK** 11 이상
- **Android SDK** API Level 21 (Lollipop) 이상
- Android 기기 또는 에뮬레이터

### Installation

1. **저장소 클론**
   ```bash
   git clone https://github.com/johnkim6823/Android-programming.git
   ```

2. **Android Studio에서 프로젝트 열기**
   - Android Studio 실행
   - **File > Open** 선택 후 원하는 프로젝트 디렉토리 선택 (예: `Calculator20220314`)
   - Gradle 동기화 및 종속성 다운로드 대기

3. **앱 실행**
   - Android 기기 연결 또는 에뮬레이터 시작
   - **Run** 버튼 클릭 또는 `Shift+F10`

## Tech Stack

- **Language:** Java
- **Build System:** Gradle
- **Database:** SQLite
- **Min SDK:** API 21 (Android 5.0 Lollipop)
- **Target SDK:** API 32 (Android 12L)

## License

This repository is licensed under the MIT License. Feel free to use, modify, and distribute these projects as needed.
